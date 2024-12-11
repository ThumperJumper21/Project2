function retval = PSS2_Smoothing(input1, input2)
    % Generate Salted Data
    x = -10:0.1:10; % x-values from -10 to 10
    y = 2 * x + 1; % Original linear function (y = 2x + 1)
    rng(42); % Random seed for reproducibility
    salt = rand(size(y)) * 2 - 1; % Random noise between -1 and 1
    y_salted = y + salt; % Salted data (original + noise)

    % Plot Original and Salted Data

    figure; %Starts the figure
    plot(x, y, 'b-', 'LineWidth', 1.5); hold on; % Line of Axes 1.5
    plot(x, y_salted, 'r.', 'MarkerSize', 10);  % Point Size 10
    title('Original vs Salted Data');
    xlabel('x'); %Labels X axis
    ylabel('y'); %Labels Y axis
    legend('Original Data', 'Salted Data'); % The legend tokens change
    grid on; % Grid on

    % Apply Moving Average Smoothing
    if nargin < 2
        windowSize = 3; % Default window size
    else
        windowSize = input2; % Use provided window size
    end
    y_smoothed = movmean(y_salted, windowSize); % Smoothed data

    % Plot Smoothed Data

    figure;
    plot(x, y_salted, 'r.', 'MarkerSize', 10); hold on; % Points Size 10
    plot(x, y_smoothed, 'g-', 'LineWidth', 1.5); % Line Width 1.5
    title('Salted vs Smoothed Data'); % Changes the title
    xlabel('x'); % Label X axis
    ylabel('y'); % Label Y axis
    legend('Salted Data', 'Smoothed Data'); % The legend tokens change
    grid on; % Grid On

    % Export Data to CSV
    output = [x', y_salted', y_smoothed']; % Combine data into columns
    csvwrite('smoothed_data.csv', output); %Write into CSV with this name
    disp('Data exported to smoothed_data.csv'); %Shows output

    % Output smoothed data
    retval = output; % Return smoothed data for further analysis if needed
endfunction
